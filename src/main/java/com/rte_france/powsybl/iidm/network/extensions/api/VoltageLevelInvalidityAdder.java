/*
  Copyright (c) 2026, RTE (http://www.rte-france.com)
  This Source Code Form is subject to the terms of the Mozilla Public
  License, v. 2.0. If a copy of the MPL was not distributed with this
  file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.rte_france.powsybl.iidm.network.extensions.api;

import com.powsybl.commons.extensions.ExtensionAdder;
import com.powsybl.iidm.network.VoltageLevel;

/**
 * @author Joris Mancini <joris.mancini_externe at rte-france.com>
 */
public interface VoltageLevelInvalidityAdder extends ExtensionAdder<VoltageLevel, VoltageLevelInvalidity> {

    @Override
    default Class<? super VoltageLevelInvalidity> getExtensionClass() {
        return VoltageLevelInvalidity.class;
    }

    VoltageLevelInvalidityAdder withInvalidityType(VoltageLevelInvalidity.InvalidityType type);
}
