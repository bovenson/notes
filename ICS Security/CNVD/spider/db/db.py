#!/bin/python3
# coding: utf-8

"""
CNVD漏洞列表(http://ics.cnvd.org.cn/) 爬虫
数据库操作
"""
from time import sleep
import pymysql

from device_parser.device_parser import DeviceParser

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-22 10:11"

HOST = 'localhost'
USER = 'localhost'
PASSWORD = 'localhost'
# DB_SCHEMA = 'vulnerability_detection_db'
DB_SCHEMA = 'cnvd'


class CNVDOperator:
    def __init__(self):
        self.db = pymysql.connect(host=HOST, user=USER, passwd=PASSWORD, db=DB_SCHEMA, charset='utf8')
        self.cursor = self.db.cursor()
        self.table_fields = {}
        self.table_name = 'cnvddetail'

    def insert(self, meta_data, separate_device=True):
        """向表CNVD中插入记录"""
        if separate_device:
            self.insert_cnvd_record(meta_data)
            return

        self.insert_cnvd_record_per(meta_data)

    def insert_cnvd_record(self, meta_data):
        devices_str = meta_data.get('device')
        if devices_str:
            print(devices_str)
            devices = devices_str.split('\\n')
            print(devices)
            # sleep(100000)
            for device in devices:
                device = device.strip()
                if len(device) > 0:
                    meta_data['product_name'] = device
                    self.insert_cnvd_record_per(meta_data)
        else:
            self.insert_cnvd_record_per(meta_data)

    def insert_cnvd_record_per(self, meta_data):
        table_fields = self.get_table_fields(self.table_name)
        legal_fields = []
        field_values = []
        for field in table_fields:
            if meta_data.get(field):
                legal_fields.append(field)
                field_values.append(meta_data.get(field).replace("'", "\\'"))
        sql_statement = "INSERT INTO " + self.table_name + "("
        sql_statement += ', '.join(legal_fields)
        sql_statement += ") VALUES('"
        sql_statement += "', '".join(field_values)
        sql_statement += "')"
        # print(sql_statement)
        # print(sql_statement)
        self.cursor.execute(sql_statement)
        self.db.commit()
        print('插入记录:', meta_data.get('CNVD_ID'))

    def get_all_cnvd_id(self)->set:
        """获取数据库所有漏洞的cnvd id"""
        res = set()
        sql_statement = 'SELECT CNVD_ID FROM ' + self.table_name
        self.cursor.execute(sql_statement)
        for row in self.cursor.fetchall():
            res.add(row[0])
        return res

    def get_table_fields(self, table_name: str)->list:
        """
        获取表字段
        :param table_name: 表名称
        :return: 字段列表
        """
        if self.table_fields.get(table_name):
            return self.table_fields.get(table_name)
        else:
            res = []
            sql_statement = "SELECT * FROM " + table_name
            self.cursor.execute(sql_statement)
            for field in self.cursor.description:
                res.append(field[0])
            self.table_fields[table_name] = res
            return res

    def get_device_parser_rules(self):
        """获取提取设备型号等信息规则"""
        res = []
        sql_statement = "SELECT reg_exp FROM device_reg_exp"
        self.cursor.execute(sql_statement)
        for row in self.cursor.fetchall():
            print(row[0])
            res.append(row[0])
        return res

    def insert_device_parser_rules(self, reg_exp):
        """插入设备型号提取正则表达式"""
        reg_exp.replace("'", "\'")
        sql_statement = "INSERT INTO device_reg_exp (reg_exp) VALUES ('%s')" % reg_exp
        print(sql_statement)
        self.cursor.execute(sql_statement)
        self.db.commit()

    def update_device_info(self):
        sql_statement = "SELECT id, product_name from cnvddetail"
        self.cursor.execute(sql_statement)
        for row in self.cursor.fetchall():
            record_id, product_name = row
            parse_result = DeviceParser.parse(product_name)
            parse_result if parse_result is None else list(map(lambda x: print(x.groupdict()), parse_result))


if __name__ == "__main__":
    meta_data_sample = {'Vulnerability_name': 'Cisco IOS Software拒绝服务漏洞（CNVD-2017-34216）',
                        '报送者': '恒安嘉新(北京)科技股份公司', 'CNVD_ID': 'CNVD-2017-34216',
                        'Release_time': '2017-11-16', 'Level': '高', 'Device': 'Cisco IOS Software 0',
                        'Description': '''Cisco IOS是美国思科（Cisco）公司为其网络设备开发的操作系统。 Cisco IOS中的PROFINET Discovery and Configuration Protocol (PN-DCP)""的实现存'在拒绝服'务漏洞，该漏洞源于程序未能正确的解析PN-DCP Identify Request数据包。远程攻击者可通过发送特制的PN-DCP Identify Request数据包利用该漏洞造成拒绝服务（重新加载）。''',
                        'Refer_link': 'https://tools.cisco.com/security/center/content/CiscoSecurityAdvisory/'
                                      'cisco-sa-20170927-profinet http://www.securityfocus.com/bid/101043',
                        '漏洞解决方案': '目前厂商已发布升级补丁以修复漏洞，补丁获取链接： https://tools.cisco.com/security/'
                                  'center/content/CiscoSecurityAdvisory/cisco-sa-20170927-profinet',
                        'Vendor': 'Cisco', '厂商补丁': 'Cisco IOS Software拒绝服务漏洞（CNVD-2017-34216）的补丁',
                        '验证信息': '(暂无验证信息)', '报送时间': '2017-09-28',
                        '收录时间': '2017-11-16', '更新时间': '2017-11-16', '漏洞附件': '(无附件)'}
    # db = pymysql.connect(HOST, USER, PASSWORD, DB_SCHEMA)
    # cursor = db.cursor()
    # sql = 'SELECT * from cnvd'
    # cursor.execute(sql)
    # for i in cursor.description:
    # print(i)
    #    print(i[0])
    cnvd = CNVDOperator()
    # cnvd.insert(meta_data=meta_data_sample)
    # print(cnvd.get_all_cnvd_id())
    # cnvd.get_device_parser_rules()
    # rule = "''''^\s*(?P<manufacturer>\s*Rockwell Automation Allen-Bradley\s*)(?P<device>MicroLogix\s*\d+)\s*(?P<type>\d+-[a-zA-Z0-9]+)\s*(?P<version>[<=>]*[0-9]+[.0-9]*)\s*$"
    # cnvd.insert_device_parser_rules(rule)
    cnvd.update_device_info()
