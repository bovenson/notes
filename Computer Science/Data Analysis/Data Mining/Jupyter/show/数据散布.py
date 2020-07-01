
# coding: utf-8

# In[1]:


import pandas as pd
import matplotlib.pyplot as plt 
get_ipython().run_line_magic('matplotlib', 'inline')


# In[2]:


# 加载数据集
train = pd.read_csv('./data/train.csv')


# In[3]:


train.head(5)


# In[4]:


age = train['Age']


# In[5]:


# 极差
d_range = age.max() - age.min()
print(d_range)


# In[6]:


# 四分位数
quartile = age.quantile([.25, .5, .75])
print(quartile)


# In[7]:


# 四分位数极差
quartile = quartile.values
IQR = quartile[2] - quartile[0]
print(IQR)


# In[9]:


# 五数概括
q1 = quartile[0]
q3 = quartile[2]
minimum = age.min()
maxinum = age.max()
median = quartile[1]
print(minimum, q1, median, q3, maxinum)


# In[10]:


# 绘制盒图
# 年龄数据切分
ages = [age.values[i*200: (i+1)*200] for i in range(4)]

# 绘制盒图
age_df = pd.DataFrame({
    'part 1': ages[0],
    'part 2': ages[1],
    'part 3': ages[2],
    'part 4': ages[3],
})

age_df.boxplot()
plt.show()


# In[11]:


# 计算方差
age.var()


# In[12]:


# 计算标准差
age.std()

