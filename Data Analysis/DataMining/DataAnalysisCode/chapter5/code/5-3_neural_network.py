#-*- coding: utf-8 -*-
#ʹ���������㷨Ԥ�������ߵ�

import pandas as pd

#������ʼ��
inputfile = '../data/sales_data.xls'
data = pd.read_excel(inputfile, index_col = u'���') #��������

#����������ǩ��Ҫ����ת��Ϊ����
#��1����ʾ���á������ǡ������ߡ����������ԣ���0����ʾ�����������񡱡����͡�
data[data == u'��'] = 1
data[data == u'��'] = 1
data[data == u'��'] = 1
data[data != 1] = 0
x = data.iloc[:,:3].as_matrix().astype(int)
y = data.iloc[:,3].as_matrix().astype(int)

from keras.models import Sequential
from keras.layers.core import Dense, Activation

model = Sequential() #����ģ��
model.add(Dense(input_dim = 3, output_dim = 10))
model.add(Activation('relu')) #��relu������Ϊ��������ܹ�����ṩ׼ȷ��
model.add(Dense(input_dim = 10, output_dim = 1))
model.add(Activation('sigmoid')) #������0-1�������sigmoid������Ϊ�����

model.compile(loss = 'binary_crossentropy', optimizer = 'adam', class_mode = 'binary')
#����ģ�͡��������������Ƕ�Ԫ���࣬��������ָ����ʧ����Ϊbinary_crossentropy���Լ�ģʽΪbinary
#���ⳣ������ʧ��������mean_squared_error��categorical_crossentropy�ȣ����Ķ������ļ���
#��ⷽ������ָ����adam������sgd��rmsprop�ȿ�ѡ

model.fit(x, y, nb_epoch = 1000, batch_size = 10) #ѵ��ģ�ͣ�ѧϰһǧ��
yp = model.predict_classes(x).reshape(len(y)) #����Ԥ��

from cm_plot import * #�������б�д�Ļ���������ӻ�����
cm_plot(y,yp).show() #��ʾ����������ӻ����
