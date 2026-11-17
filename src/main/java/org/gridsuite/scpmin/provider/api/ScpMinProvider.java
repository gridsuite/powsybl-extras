/**
 * Copyright (c) 2026, RTE (http://www.rte-france.com)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.gridsuite.scpmin.provider.api;

import com.powsybl.commons.Versionable;
import com.powsybl.commons.config.PlatformConfig;
import com.powsybl.commons.config.PlatformConfigNamedProvider;
import com.powsybl.commons.report.ReportNode;
import com.powsybl.computation.ComputationManager;
import com.powsybl.iidm.network.Network;
import com.powsybl.shortcircuit.ShortCircuitParameters;
import org.gridsuite.scpmin.provider.api.dto.ScpMinResult;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.concurrent.CompletableFuture;

public interface ScpMinProvider extends Versionable, PlatformConfigNamedProvider {

    static List<ScpMinProvider> findAll() {
        List<ScpMinProvider> providers = new ArrayList<>();
        ServiceLoader.load(ScpMinProvider.class, ScpMinProvider.class.getClassLoader()).forEach(providers::add);
        return providers;
    }

    static ScpMinProvider find(String name) {
        return PlatformConfigNamedProvider.Finder.find(name, "scp-min", ScpMinProvider.class, PlatformConfig.defaultConfig());
    }

    /**
     * Starts a computation. Cancelling the returned future must cancel the underlying computation.
     */
    CompletableFuture<ScpMinResult> runAsync(Network network, List<String> busIds,
                                             ShortCircuitParameters parameters, ComputationManager computationManager,
                                             ReportNode reportNode);
}
