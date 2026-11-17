/**
 * Copyright (c) 2026, RTE (http://www.rte-france.com)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.gridsuite.powsybl.extras.iidm.network.extensions.api;

import com.powsybl.iidm.network.VoltageLevel;

import java.util.Optional;
import java.util.ServiceLoader;

public final class VoltageLevelInvalidityAccessor {

    private static final Provider PROVIDER = ServiceLoader.load(Provider.class)
            .findFirst()
            .orElseThrow(() -> new IllegalStateException(
                    "No VoltageLevelInvalidityAccessor provider found; add gridsuite-powsybl-rte-extras at runtime"));

    private VoltageLevelInvalidityAccessor() {
    }

    public enum InvalidityType {
        ERRONEOUS_TOPOLOGY_PQ,
        ERRONEOUS_TOPOLOGY_PV,
        NOT_FULLY_DESCRIBED
    }

    public static Optional<InvalidityType> getInvalidityType(VoltageLevel voltageLevel) {
        return PROVIDER.getInvalidityType(voltageLevel);
    }

    public interface Provider {
        Optional<InvalidityType> getInvalidityType(VoltageLevel voltageLevel);
    }
}
