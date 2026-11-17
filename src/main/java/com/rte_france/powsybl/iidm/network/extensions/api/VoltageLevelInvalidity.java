/*
  Copyright (c) 2026, RTE (http://www.rte-france.com)
  This Source Code Form is subject to the terms of the Mozilla Public
  License, v. 2.0. If a copy of the MPL was not distributed with this
  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rte_france.powsybl.iidm.network.extensions.api;

import com.powsybl.commons.extensions.Extension;
import com.powsybl.iidm.network.VoltageLevel;
import org.jspecify.annotations.NonNull;

/**
 * @author Joris Mancini <joris.mancini_externe at rte-france.com>
 */
public interface VoltageLevelInvalidity extends Extension<VoltageLevel> {

    enum InvalidityType {
        ERRONEOUS_TOPOLOGY_PQ,
        ERRONEOUS_TOPOLOGY_PV,
        NOT_FULLY_DESCRIBED
    }

    String NAME = "voltageLevelInvalidity";

    @Override
    default String getName() {
        return NAME;
    }

    @NonNull InvalidityType getInvalidityType();
}
