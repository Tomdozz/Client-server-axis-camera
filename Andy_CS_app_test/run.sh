#!/bin/sh
eap-install.sh stop
create-package.sh mipsisa32r2el
eap-install.sh 192.168.20.247 pass install
eap-install.sh start
