#!/bin/python3
# coding: utf-8

"""
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2018-06-03 09:58"

import requests
import json


def obtain_token():
    auth = '''
    {
        "auth": {
            "identity": {
                "methods": [
                    "password"
                ],
                "password": {
                    "user": {
                        "id": "7984a9af21434a0494cb755dc93ce41a",
                        "password": "cloud"
                    }
                }
            },
            "scope": "unscoped"
        }
    }
    '''
    url = 'http://192.168.1.10:35357/v3/auth/tokens'
    r = requests.post(url, data=auth)
    r = json.loads(r.text)
    return r['token']


obtain_token()
