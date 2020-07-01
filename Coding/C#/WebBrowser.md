# WebBrowser

## 判断页面是否加载完成

`webBrowser.ReadyState == WebBrowserReadyState.Complete`

## 加载一个页面, DocumentCompleted事件被调用多次 

浏览器在加载完成一个页面的过程中, 可能会多次多次触发`DocumentCompleted`事件(因为页面中的`IFrame`控件的加载完成也会触发该事件), 导致多次调用事件处理函数 `webBrowser_DocumentCompleted(object sender, WebBrowserDocumentCompletedEventArgs e)`, 可在该函数中通过`e.Url.AbsolutePath == webBrowser.Url.AbsolutePath`来判断, 是否是页面最终加载完成. 

[参考](http://stackoverflow.com/questions/12166870/webbrowser-and-documentcompleted-event)

