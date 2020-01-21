# Sieci Sterowane Programowo - Projekt
## Load balancer z wykorzystaniem algorytmu Bellmana-Forda

Funkcjonalność została dodana jako nowy moduł kontrolera Floodlight.

##Cel
Celem projektu było dynamiczne wybieranie ścieżki dla przychodzącego flow.
Osiągnięte zostało to z wykorzystaniem algorytmu Bellmana-Forda. Dla każdego przepływu
optymalna trasa to ta wyliczona przez algorytm (suma wag ścieżek). Po przepuszczeniu flow 
daną trasą jej waga jest zwiększana. Dla kolejnego przepływu liczenie odbędzie się z uwzględnioną nową wagą.

##Wymagania
<li>Linux environment</li>
<li>openvswitch service</li>
<li>Python</li>
<li>Java 8</li>
<li>mininet</li>
<li>ant</li>

##Implementacja
W folderze src znajdują się źródła jak i topologia, która zostaje wczytana przez symulator mininet.<br>
Aby uruchomić minineta z zewnętrznym kontrolerem należy wpisać:<br>
`sudo mn --custom=<topology_file.py> --topo=<topology> --controller=remote, ip=127.0.0.1, port=6653`

Folder src to testy implementacji algorytmu Bellmana-Forda


