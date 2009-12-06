#!/bin/bash

echo "instalando fone@RNP Agente v1.0"

install -D fonernp-agente.jar /opt/fonernp-agente/fonernp-agente.jar
cp .java.policy /root
cp -R .fonernp /root
cp fonernp-agente.sql /root

echo "sistema instalado com sucesso."
