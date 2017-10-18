#-*- coding: utf-8 -*-
#�߼��ع� �Զ���ģ
import pandas as pd

#������ʼ��
filename = '../data/bankloan.xls'
data = pd.read_excel(filename)
x = data.iloc[:,:8].as_matrix()
y = data.iloc[:,8].as_matrix()

from sklearn.linear_model import LogisticRegression as LR
from sklearn.linear_model import RandomizedLogisticRegression as RLR 
rlr = RLR() #��������߼��ع�ģ�ͣ�ɸѡ����
rlr.fit(x, y) #ѵ��ģ��
rlr.get_support() #��ȡ����ɸѡ�����Ҳ����ͨ��.scores_������ȡ���������ķ���
print(u'ͨ������߼��ع�ģ��ɸѡ����������')
print(u'��Ч����Ϊ��%s' % ','.join(data.columns[rlr.get_support()]))
x = data[data.columns[rlr.get_support()]].as_matrix() #ɸѡ������

lr = LR() #�����߼�����ģ��
lr.fit(x, y) #��ɸѡ�������������ѵ��ģ��
print(u'�߼��ع�ģ��ѵ��������')
print(u'ģ�͵�ƽ����ȷ��Ϊ��%s' % lr.score(x, y)) #����ģ�͵�ƽ����ȷ�ʣ�����Ϊ81.4%