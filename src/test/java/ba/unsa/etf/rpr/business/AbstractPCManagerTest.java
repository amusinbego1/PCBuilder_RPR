package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.beans.GraphCardBean;
import ba.unsa.etf.rpr.beans.PCComponent;
import ba.unsa.etf.rpr.beans.ProcessorBean;
import ba.unsa.etf.rpr.beans.RamBean;
import ba.unsa.etf.rpr.beans.decorator.pc.PC;
import ba.unsa.etf.rpr.beans.decorator.pc.PCBean;
import ba.unsa.etf.rpr.dal.pc.AbstractPCDao;
import ba.unsa.etf.rpr.dal.pc.PCDaoImpl;
import ba.unsa.etf.rpr.exceptions.PCBuilderException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Order(5)
class AbstractPCManagerTest {

    private static final AbstractPCManager pcManager = spy(new PCManager());
    private static AbstractPCDao dao;

    private List<PC> pcs;

    @BeforeAll
    public static void setupAll() {
        dao = mock(PCDaoImpl.class);
        doReturn(dao).when(pcManager).getDao();
    }

    @BeforeEach
    public void setupEach() {
        pcs = new ArrayList<>(List.of(
                new PCBean(
                        new ArrayList<>(List.of(new ProcessorBean(), new RamBean(), new GraphCardBean()))
                ),
                new PCBean(
                        new ArrayList<>(List.of(new ProcessorBean(), new RamBean(), new GraphCardBean()))
                )
        ));
    }

    @Test
    @DisplayName("Remove all pcs from DB")
    void testRemoveAll_RemoveAllPCs_ShouldReturnEmptyCollection() throws PCBuilderException {
        doAnswer(invocation -> {
            pcs.clear();
            return pcs;
        }).when(dao).removeAll();
        doReturn(pcs).when(dao).getAll();

        pcManager.removeAll();
        assertEquals(0, pcManager.getAll().size());
    }

    private static Object[] makePCsTestParametersSuccess() {
        return new Object[]{
                new Object[]{
                        new ArrayList<PCComponent>(List.of(
                                new ProcessorBean(), new ProcessorBean()
                        )),
                        new ArrayList<PCComponent>(List.of(
                                new RamBean(), new RamBean()
                        )),
                        new ArrayList<PCComponent>(List.of(
                                new GraphCardBean(), new GraphCardBean()
                        )),
                        8
                },
                new Object[]{
                        new ArrayList<PCComponent>(List.of(
                                new ProcessorBean(), new ProcessorBean()
                        )),
                        new ArrayList<PCComponent>(List.of(
                                new RamBean(), new RamBean()
                        )),
                        new ArrayList<PCComponent>(List.of(
                                new GraphCardBean()
                        )),
                        4
                },
                new Object[]{
                        new ArrayList<PCComponent>(List.of(
                                new ProcessorBean()
                        )),
                        new ArrayList<PCComponent>(List.of(
                                new RamBean(), new RamBean()
                        )),
                        new ArrayList<PCComponent>(List.of(
                                new GraphCardBean()
                        )),
                        2
                },
                new Object[]{
                        new ArrayList<PCComponent>(List.of(
                                new ProcessorBean(), new ProcessorBean()
                        )),
                        new ArrayList<PCComponent>(),
                        new ArrayList<PCComponent>(List.of(
                                new GraphCardBean(), new GraphCardBean()
                        )),
                        0
                },
                new Object[]{
                        new ArrayList<PCComponent>(),
                        new ArrayList<PCComponent>(List.of(
                                new RamBean(), new RamBean()
                        )),
                        new ArrayList<PCComponent>(List.of(
                                new GraphCardBean()
                        )),
                        0
                },
                new Object[]{
                        new ArrayList<PCComponent>(List.of(
                                new ProcessorBean()
                        )),
                        new ArrayList<PCComponent>(List.of(
                                new RamBean(), new RamBean()
                        )),
                        new ArrayList<PCComponent>(),
                        0
                }
        };
    }

    @ParameterizedTest
    @MethodSource("makePCsTestParametersSuccess")
    @DisplayName("Make list of PCs from provided PCComponent objects (success)")
    void testMakePCs_MakePCsListFromProvidedPCComponents_ShouldReturnCorrectSizeOfPCsList(List<PCComponent> cp1, List<PCComponent> cp2, List<PCComponent> cp3, int expectedSize) throws PCBuilderException {
        assertEquals(expectedSize, pcManager.makePCs(cp1, cp2, cp3).size());
    }

    @Test
    @DisplayName("Add provided PCs as List to db")
    void testAddPCs_AddProvidedPCsAsListToDBMock_ShouldReturnCurrenSizePlusAddedPCs() throws PCBuilderException {
        int oldSize = pcs.size();
        doAnswer(invocation -> {
            pcs.addAll(invocation.getArgument(0));
            return pcs;
        }).when(pcManager).addPCs(anyList());
        pcManager.addPCs(List.of(new PCBean(), new PCBean(), new PCBean()));
        assertEquals(oldSize + 3, pcs.size());
    }

    @Test
    @DisplayName("Add provided PCs as VarArgs to db")
    void testAddPCs_AddProvidedPCsAsVarArgsToDBMock_ShouldReturnCurrenSizePlusAddedPCs() throws PCBuilderException {
        int oldSize = pcs.size();
        doAnswer(invocation -> {
            pcs.addAll(invocation.getArgument(0));
            return pcs;
        }).when(pcManager).addPCs(anyList());
        pcManager.addPCs(new PCBean(), new PCBean(), new PCBean());
        assertEquals(oldSize + 3, pcs.size());
    }


}