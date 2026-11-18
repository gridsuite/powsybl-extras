/**
 * Copyright (c) 2026, RTE (http://www.rte-france.com)
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.gridsuite.powsybl.extras;

import com.google.auto.service.AutoService;
import com.powsybl.tools.AbstractVersion;
import com.powsybl.tools.Version;

/**
 * @author Antoine Bouhours <antoine.bouhours at rte-france.com>
 */
@AutoService(Version.class)
public class PowsyblExtrasVersion extends AbstractVersion {

    public PowsyblExtrasVersion() {
        super("powsybl-extras", "${project.version}", "${buildNumber}", "${scmBranch}", Long.parseLong("${timestamp}"));
    }
}
