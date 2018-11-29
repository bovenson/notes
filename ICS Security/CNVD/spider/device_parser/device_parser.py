#!/bin/python3
# coding: utf-8

"""
解析 影响产品 字段
"""
import re

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-12-18 14:39"

rules = [
    '^\s*(?P<manufacturer>Philips)\s*(?P<device>[a-zA-Z ]+)\s*(?P<version>([<=>]*[0-9]+[.0-9]*[， ]*)+)\s*$',
    '^\s*(?P<manufacturer>\s*Rockwell Automation Allen-Bradley\s*)(?P<device>MicroLogix\s*\d+)\s*(?P<type>\d+-[a-zA-Z0-9]+)\s*(?P<version>[<=>]*[0-9]+[.0-9]*)\s*$',
    '^\s*(?P<manufacturer>\s*siemens\s*)(?P<device>[a-zA-Z 0-9\-]+)(?P<version>[<=>]*[0-9]+[.0-9]*)?\s*$',
    '^\s*(?P<manufacturer>\s*siemens\s*)(?P<device>[a-zA-Z 0-9\-]+)(?P<version>[><+v]*[.0-9 ]+(SP[.0-9]*)?)?\s*$',
    '^\s*(?P<manufacturer>\s*Boston\s*)(?P<device>[a-zA-Z 0-9\-]+)(?P<version>Model\s*[0-9]+)\s*$',
    '^\s*(?P<manufacturer>\s*Abbott\s*)(?P<device>[a-zA-Z 0-9\-]+)(?P<version>[<>=]*[a-zA-Z]+\s+[0-9]+，[0-9]+)\s*$',
]

rules_re_compile = [re.compile(rule, re.IGNORECASE) for rule in rules ]


class DeviceParser:
    @staticmethod
    def parse(device_info):
        best_match = None
        for rule in rules_re_compile:
            res = list(rule.finditer(device_info))
            if len(res) > 0:
                best_match = res
                break
        # print(best_match) if best_match is None else list(map(lambda x: print(x.groupdict()), best_match))
        return best_match
    pass


devices = [
    """ 
    Philips DoseWise Portal >=1.1.7.333，<=2.1.1.3069
    Rockwell Automation Allen-Bradley MicroLogix 1100 1763-L16AWA <=16.00 
Rockwell Automation Allen-Bradley MicroLogix 1100 1763-L16BBB <=16.00 
Rockwell Automation Allen-Bradley MicroLogix 1100 1763-L16BWA <=16.00 
Rockwell Automation Allen-Bradley MicroLogix 1100 1763-L16DWD <=16.00 
Rockwell Automation Allen-Bradley MicroLogix 1400 1766-L32AWA <=16.00 
Rockwell Automation Allen-Bradley MicroLogix 1400 1766-L32BWA <=16.00 
Rockwell Automation Allen-Bradley MicroLogix 1400 1766-L32BWAA <=16.00 
Rockwell Automation Allen-Bradley MicroLogix 1400 1766-L32BXB <=16.00 
Rockwell Automation Allen-Bradley MicroLogix 1400 1766-L32BXBA <=16.00 
Rockwell Automation Allen-Bradley MicroLogix 1400 1766-L32AWAA <=16.00
    SIEMENS SIMATIC PCS 7 8.0
    SIEMENS SIMATIC PCS 7 8.1
 SIEMENS SIMATIC PCS 7 <=7.1
 SIEMENS SIMATIC WinCC 7.3 
 SIEMENS SIMATIC WinCC 7.2
 SIEMENS SIMATIC WinCC 7.0
 SIEMENS SIMATIC WinCC <V7.4 SP1
 SIEMENS SIMATIC WinCC Runtime Professional <V14 SP1
 SIEMENS SIMATIC WinCC Runtime Professional <V13
 SIEMENS SIMATIC NET PC-Software
 SIEMENS SIMATIC IT Production Suite All
""", """
Siemens 7KM PAC Switched Ethernet PROFINET expansion module <2.1.3 
""", """
Boston Scientific ZOOM LATITUDE PRM Model 3120
""", """
Abbott Laboratories Accent <August 28，2017
 Abbott Laboratories Anthem <August 28，2017
 Abbott Laboratories Accent MRI <August 28，2017
 Abbott Laboratories Assurity MRI <August 28，2017
"""
]

if __name__ == "__main__":
    for item in devices:
        for line in item.split('\n'):
            _line = line.strip()
            if len(_line) > 0:
                DeviceParser().parse(_line)
    pass
