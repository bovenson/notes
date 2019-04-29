# 安装

- 下载
  - https://www.eclipse.org/mat/

# 超大Dump文件分析

## 步骤

- jmap 导出堆栈

  ```shell
  $ jmap -dump:live,format=b,file=<file-name> <pid>
  ```

- 运行

- ```shell
  $ ./ParseHeapDump.sh <file-name> org.eclipse.mat.api:suspects org.eclipse.mat.api:overview org.eclipse.mat.api:top_components
  ```

- 编写代码查看堆栈信息

  ```java
  import org.eclipse.mat.SnapshotException;
  import org.eclipse.mat.parser.model.PrimitiveArrayImpl;
  import org.eclipse.mat.snapshot.ISnapshot;
  import org.eclipse.mat.parser.internal.SnapshotFactory;
  import org.eclipse.mat.snapshot.model.IClass;
  import org.eclipse.mat.snapshot.model.IObject;
  import org.eclipse.mat.util.ConsoleProgressListener;
  import org.eclipse.mat.util.IProgressListener;
  import java.io.File;
  import java.io.IOException;
  import java.util.Collection;
  import java.util.HashMap;
  public class Main {
      public static void main(String[] args) throws SnapshotException, IOException {
              String arg = args[args.length - 1];
          String fileName = arg;
          IProgressListener listener = new ConsoleProgressListener(System.out);
          SnapshotFactory sf = new SnapshotFactory();
          ISnapshot snapshot = sf.openSnapshot(new File(fileName),
                  new HashMap<String, String>(), listener);
          System.out.println(snapshot.getSnapshotInfo());
          System.out.println();
          String[] classNames = {"java.lang.String"};
          for (String name : classNames) {
              Collection<IClass> classes = snapshot.getClassesByName(name, false);
              if (classes == null || classes.isEmpty()) {
                  System.out.println(String.format("Cannot find class %s in heap dump", name));
                  continue;
              }
              assert classes.size() == 1;
              IClass clazz = classes.iterator().next();
              int[] objIds = clazz.getObjectIds();
              long minRetainedSize = snapshot.getMinRetainedSize(objIds, listener);
              System.out.println(String.format("%s instances = %d, retained size >= %d", clazz.getName(), objIds.length, minRetainedSize));
              for (int i = 0; i < objIds.length; i++) {
                  IObject str = snapshot.getObject(objIds[i]);
                  String address = Long.toHexString(snapshot.mapIdToAddress(objIds[i]));
                  PrimitiveArrayImpl chars = (PrimitiveArrayImpl) str.resolveValue("value");
                  String value = new String((char[]) chars.getValueArray());
                  System.out.println(String.format("id=%d, address=%s, value=%s", objIds[i], address, value));
              }
          }
      }
  }
  ```

## 设置使用内存

- 打开 `MemoryAnalyzer.ini`
- 修改 `-Xmx1024m`

## 参考

- https://stackoverflow.com/questions/7254017/tool-for-analyzing-large-java-heap-dumps
- https://colobu.com/2015/08/12/using-Eclipse-Memory-Analyzer-by-the-command-line