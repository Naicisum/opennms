/*
 * This file is part of the OpenNMS(R) Application.
 *
 * OpenNMS(R) is Copyright (C) 2008 The OpenNMS Group, Inc.  All rights reserved.
 * OpenNMS(R) is a derivative work, containing both original code, included code and modified
 * code that was published under the GNU General Public License. Copyrights for modified
 * and included code are below.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * Modifications:
 *
 * Original code base Copyright (C) 1999-2001 Oculan Corp.  All rights reserved.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 *
 * For more information contact:
 *      OpenNMS Licensing       <license@opennms.org>
 *      http://www.opennms.org/
 *      http://www.opennms.com/
 *
 */

package org.opennms.netmgt.provision.scan.snmp;

import java.net.InetAddress;

import org.opennms.netmgt.dao.SnmpAgentConfigFactory;
import org.opennms.netmgt.provision.ScanContext;
import org.opennms.netmgt.provision.Scanner;
import org.opennms.netmgt.snmp.CollectionTracker;
import org.opennms.netmgt.snmp.SnmpAgentConfig;
import org.opennms.netmgt.snmp.SnmpUtils;
import org.opennms.netmgt.snmp.SnmpWalker;
import org.springframework.util.Assert;

/**
 * @author brozow
 *
 */
public class AbstractSnmpScanner implements Scanner {
    
    private String m_name = null;
    private SnmpAgentConfigFactory m_snmpAgentConfigFactory = null;
    
    protected AbstractSnmpScanner(String name) {
        m_name = name;
    }
    
    public String getName() {
        return m_name;
    }
    
    public void setSnmpAgentConfigFactory(SnmpAgentConfigFactory snmpPeerFactory) {
        m_snmpAgentConfigFactory = snmpPeerFactory;
    }

    /* (non-Javadoc)
     * @see org.opennms.netmgt.provision.Scanner#init()
     */
    public void init() {
        Assert.notNull(m_snmpAgentConfigFactory, "snmpAgentConfigFactory must be set");

        
        onInit();
        
    }

    protected void onInit() {
    }

    /* (non-Javadoc)
     * @see org.opennms.netmgt.provision.Scanner#scan(org.opennms.netmgt.provision.ScanContext)
     */
    public void scan(ScanContext context) throws InterruptedException {
        InetAddress agentAddress = context.getAgentAddress("SNMP");
        if (agentAddress == null) return;
        
        SnmpAgentConfig agentConfig = m_snmpAgentConfigFactory.getAgentConfig(agentAddress);
        
        SnmpWalker walker = SnmpUtils.createWalker(agentConfig, getName(), createCollectionTracker(context));
        walker.start();
        
        walker.waitFor();
        
        
        
    }

    /**
     * @param context
     * @return
     */
    private CollectionTracker createCollectionTracker(ScanContext scanContext) {
        throw new UnsupportedOperationException("AbstractSnmpScanner.createCollectionTracker is not yet implemented");
        
    }

}
