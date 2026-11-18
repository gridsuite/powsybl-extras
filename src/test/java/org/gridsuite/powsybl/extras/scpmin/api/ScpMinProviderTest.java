/**
 * Copyright (c) 2026, RTE (http://www.rte-france.com)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.gridsuite.powsybl.extras.scpmin.api;

import com.powsybl.commons.PowsyblException;
import com.powsybl.commons.report.ReportNode;
import com.powsybl.computation.ComputationManager;
import com.powsybl.iidm.network.Network;
import com.powsybl.shortcircuit.ShortCircuitParameters;
import org.gridsuite.powsybl.extras.scpmin.api.dto.ScpMinResult;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Antoine Bouhours <antoine.bouhours at rte-france.com>
 */
public class ScpMinProviderTest {

    @Test
    void findAllDiscoversRegisteredProvider() {
        List<ScpMinProvider> providers = ScpMinProvider.findAll();

        assertEquals(1, providers.size());
        assertInstanceOf(TestScpMinProvider.class, providers.getFirst());
        assertEquals("test-scp-min", providers.getFirst().getName());
    }

    @Test
    void findReturnsRegisteredProviderByName() {
        ScpMinProvider provider = ScpMinProvider.find("test-scp-min");

        assertInstanceOf(TestScpMinProvider.class, provider);
        assertEquals("test-scp-min", provider.getName());
    }

    @Test
    void findRejectsUnknownProviderName() {
        PowsyblException exception = assertThrows(PowsyblException.class,
                () -> ScpMinProvider.find("unknown-provider"));

        assertEquals("ScpMinProvider 'unknown-provider' not found", exception.getMessage());
    }

    public static class TestScpMinProvider implements ScpMinProvider {

        @Override
        public String getName() {
            return "test-scp-min";
        }

        @Override
        public String getVersion() {
            return "1.0";
        }

        @Override
        public CompletableFuture<ScpMinResult> runAsync(Network network, List<String> busIds,
                                                       ShortCircuitParameters parameters, ComputationManager computationManager,
                                                       ReportNode reportNode) {
            throw new UnsupportedOperationException("Test provider only supports discovery");
        }
    }
}
