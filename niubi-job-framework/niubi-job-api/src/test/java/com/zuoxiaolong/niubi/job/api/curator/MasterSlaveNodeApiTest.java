/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zuoxiaolong.niubi.job.api.curator;

import com.zuoxiaolong.niubi.job.api.data.MasterSlaveNodeData;
import com.zuoxiaolong.niubi.job.core.exception.NiubiException;
import org.apache.zookeeper.KeeperException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Xiaolong Zuo
 * @since 0.9.4.2
 */
public class MasterSlaveNodeApiTest extends AbstractZookeeperServerTest{

    @Test
    public void getAllNodesSaveNode() {
        List<MasterSlaveNodeData> list = masterSlaveNodeApi.getAllNodes();
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() == 0);
        masterSlaveNodeApi.saveNode(new MasterSlaveNodeData.Data("192.168.1.101"));
        masterSlaveNodeApi.saveNode(new MasterSlaveNodeData.Data("192.168.1.102"));
        masterSlaveNodeApi.saveNode(new MasterSlaveNodeData.Data("192.168.1.103"));
        list = masterSlaveNodeApi.getAllNodes();
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() == 3);
        List<String> ipList = Arrays.asList("192.168.1.101", "192.168.1.102", "192.168.1.103");
        for (MasterSlaveNodeData nodeData : list) {
            Assert.assertTrue(ipList.contains(nodeData.getData().getNodeIp()));
        }
    }

    @Test
    public void updateNodeGetNode() {
        String path = masterSlaveNodeApi.saveNode(new MasterSlaveNodeData.Data("192.168.1.101"));
        MasterSlaveNodeData nodeData = masterSlaveNodeApi.getNode(path);
        Assert.assertNotNull(nodeData);
        Assert.assertEquals("192.168.1.101", nodeData.getData().getNodeIp());
        masterSlaveNodeApi.updateNode(path, new MasterSlaveNodeData.Data("192.168.1.102"));
        nodeData = masterSlaveNodeApi.getNode(path);
        Assert.assertNotNull(nodeData);
        Assert.assertEquals("192.168.1.102", nodeData.getData().getNodeIp());
    }

    @Test(expected = KeeperException.NoNodeException.class)
    public void deleteNode() throws Throwable {
        String path = masterSlaveNodeApi.saveNode(new MasterSlaveNodeData.Data("192.168.1.101"));
        MasterSlaveNodeData nodeData = masterSlaveNodeApi.getNode(path);
        Assert.assertNotNull(nodeData);
        masterSlaveNodeApi.deleteNode(path);
        try {
            masterSlaveNodeApi.getNode(path);
        } catch (NiubiException e) {
            throw e.getCause();
        }
    }

}
