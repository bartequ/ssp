from mininet.topo import Topo

class MyTopo(Topo):

	def __init__(self):
		Topo.__init__(self)
		
		S1 = self.addSwitch('s1')
		S2 = self.addSwitch('s2')
		S3 = self.addSwitch('s3')
		S4 = self.addSwitch('s4')
		H1 = self.addSwitch('h1')
		H2 = self.addSwitch('h2')
		SwitchList = (S1, S2, S3, S4)

		#for i in range(0, len(SwitchList)):
		#	for j in range(i+1, len(SwitchList)):
		#		self.addLink(SwitchList[i], SwitchList[j]
		self.addLink(S1, S2)
		self.addLink(S1, S4)
		self.addLink(S1, S3)
		self.addLink(S2, S3)
		self.addLink(S2, S4)
		self.addLink(S3, S4)
	
		self.addLink(H1, S1)
		self.addLink(H2, S3)

topos = { 'mytopo': (lambda: MyTopo())}

		
