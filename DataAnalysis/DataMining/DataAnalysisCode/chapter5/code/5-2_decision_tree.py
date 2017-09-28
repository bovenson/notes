#-*- coding: utf-8 -*-
#ʹ��ID3�������㷨Ԥ�������ߵ�
import pandas as pd

#������ʼ��
inputfile = '../data/sales_data.xls'
data = pd.read_excel(inputfile, index_col = u'���') #��������

#����������ǩ��Ҫ����ת��Ϊ����
#��1����ʾ���á������ǡ������ߡ����������ԣ���-1����ʾ�����������񡱡����͡�
data[data == u'��'] = 1
data[data == u'��'] = 1
data[data == u'��'] = 1
data[data != 1] = -1
x = data.iloc[:,:3].as_matrix().astype(int)
y = data.iloc[:,3].as_matrix().astype(int)

from sklearn.tree import DecisionTreeClassifier as DTC
dtc = DTC(criterion='entropy') #����������ģ�ͣ�������Ϣ��
dtc.fit(x, y) #ѵ��ģ��

#������غ��������ӻ���������
#�����Ľ����һ��dot�ļ�����Ҫ��װGraphviz���ܽ���ת��Ϊpdf��png�ȸ�ʽ��
from sklearn.tree import export_graphviz
x = pd.DataFrame(x)
from sklearn.externals.six import StringIO
x = pd.DataFrame(x)
with open("tree.dot", 'w') as f:
  f = export_graphviz(dtc, feature_names = x.columns, out_file = f)
