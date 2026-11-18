/**
 * Copyright (c) 2026, RTE (http://www.rte-france.com)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.gridsuite.powsybl.extras.scpmin.api;

import com.google.common.collect.Lists;
import com.powsybl.commons.Versionable;
import com.powsybl.commons.config.PlatformConfig;
import com.powsybl.commons.config.PlatformConfigNamedProvider;
import com.powsybl.commons.report.ReportNode;
import com.powsybl.computation.ComputationManager;
import com.powsybl.iidm.network.Network;
import com.powsybl.shortcircuit.ShortCircuitParameters;
import org.gridsuite.powsybl.extras.scpmin.api.dto.ScpMinResult;

import java.util.List;
import java.util.ServiceLoader;
import java.util.concurrent.CompletableFuture;

/**
 * @author Antoine Bouhours <antoine.bouhours at rte-france.com>
 */
public interface ScpMinProvider extends Versionable, PlatformConfigNamedProvider {

    static List<ScpMinProvider> findAll() {
        return Lists.newArrayList(ServiceLoader.load(ScpMinProvider.class, ScpMinProvider.class.getClassLoader()));
    }

    static ScpMinProvider find(String name) {
        return PlatformConfigNamedProvider.Finder.find(name, "scp-min", ScpMinProvider.class, PlatformConfig.defaultConfig());
    }

    CompletableFuture<ScpMinResult> runAsync(Network network, List<String> busIds,
                                             ShortCircuitParameters parameters, ComputationManager computationManager,
                                             ReportNode reportNode);
}
