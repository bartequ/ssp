#!/usr/bin/python

from mininet.topo import Topo

def int2dpid( dpid ):
        try:
            dpid = hex( dpid )[ 2: ]
            dpid = '0' * ( 16 - len( dpid ) ) + dpid
            return dpid
        except IndexError:
            raise Exception( 'Unable to derive default datapath ID - '
                             'please either specify a dpid or use a '
                             'canonical switch name such as s23.' )

class Polska( Topo ):
    def __init__( self ):
        "Create custom topo."

        # Initialize topology
        Topo.__init__( self )
        # Add hosts and switches
        S1 = self.addSwitch('s1')
	    S2 = self.addSwitch('s2')
	    S3 = self.addSwitch('s3')
	    S4 = self.addSwitch('s4')

	    self.addLink(S1, S2)
	    self.addLink(S1, S4)
	    self.addLink(S1, S3)
	    self.addLink(S2, S3)
	    self.addLink(S2, S4)
	    self.addLink(S3, S4)

	    H1 = self.addHost('h1')
	    H2 = self.addHost('h2')

	    self.addLink(H1, S1)
	    self.addLink(H2, S3)

topos = { 'polska': ( lambda: Polska() ) }


