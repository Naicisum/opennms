/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2016 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2016 The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with OpenNMS(R).  If not, see:
 *      http://www.gnu.org/licenses/
 *
 * For more information contact:
 *     OpenNMS(R) Licensing <license@opennms.org>
 *     http://www.opennms.org/
 *     http://www.opennms.com/
 *******************************************************************************/

package org.opennms.features.topology.api;

import java.util.Collection;

import org.opennms.features.topology.api.browsers.SelectionAware;
import org.opennms.features.topology.api.support.breadcrumbs.BreadcrumbStrategy;
import org.opennms.features.topology.api.topo.Criteria;
import org.opennms.features.topology.api.topo.Defaults;
import org.opennms.features.topology.api.topo.GraphProvider;
import org.opennms.features.topology.api.topo.TopologyProviderInfo;
import org.opennms.features.topology.api.topo.Vertex;
import org.opennms.features.topology.api.topo.VertexRef;

public interface TopologyServiceClient extends SelectionAware {
    Vertex getVertex(VertexRef target, Criteria... criteria);

    String getNamespace();

    Vertex getVertex(String namespace, String vertexId);

    int getVertexTotalCount();

    int getEdgeTotalCount();

    TopologyProviderInfo getInfo();

    Defaults getDefaults();

//    List<Vertex> getChildren(VertexRef vertexId, Criteria[] criteria);

    Collection<GraphProvider> getGraphProviders();

    Collection<VertexRef> getOppositeVertices(VertexRef vertexRef);

    GraphProvider getGraphProviderBy(String namespace);

    GraphProvider getDefaultGraphProvider();

    LayoutAlgorithm getPreferredLayoutAlgorithm();

    BreadcrumbStrategy getBreadcrumbStrategy();

    void setMetaTopologyId(String metaTopologyId);

    String getMetaTopologyId();

    void setNamespace(String namespace);

    Graph getGraph(Criteria[] criteria, int semanticZoomLevel);
}
