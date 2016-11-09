package jd.toll.server.dao;

import jd.toll.server.domain.XBeeNode;
import jd.toll.server.services.NodeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.junit.Assert.*;

/**
 * Created by saturn on 18.10.2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class MockDaoTest {

    @Mock
    private XBeeNodeRepository xBeeNodeRepository;

    @InjectMocks
    private NodeService nodeService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveTest() throws Exception {

        XBeeNode node = new XBeeNode();
        String zigBeeId = "0013A20040F9B0F5";
        node.setZigBeeId(zigBeeId);
        nodeService.save(node);

        ArgumentCaptor<XBeeNode> captor = ArgumentCaptor.forClass(XBeeNode.class);
        verify(xBeeNodeRepository).save(captor.capture());
        XBeeNode captedNode = captor.getValue();

        assertSame("input and saved node is not the same", node, captedNode);
        assertSame("input and cached node is not the same", nodeService.getNode(zigBeeId), captedNode);

    }

}