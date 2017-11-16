package org.flyve.mdm.agent.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.InputStream;
import java.util.Properties;

/*
 *   Copyright (C) 2017 Teclib. All rights reserved.
 *
 *   This file is part of flyve-mdm-android-agent
 *
 * flyve-mdm-android-agent is a subproject of Flyve MDM. Flyve MDM is a mobile
 * device management software.
 *
 * Flyve MDM is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * Flyve MDM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * ------------------------------------------------------------------------------
 * @author    Rafael Hernandez
 * @date      2/10/17
 * @copyright Copyright (C) 2017 Teclib. All rights reserved.
 * @license   GPLv3 https://www.gnu.org/licenses/gpl-3.0.html
 * @link      https://github.com/flyve-mdm/flyve-mdm-android-agent
 * @link      https://flyve-mdm.com
 * ------------------------------------------------------------------------------
 */
public class EnvInfoAbout {
    private Properties properties = new Properties();

    private Boolean isLoaded = false;

    public EnvInfoAbout(Context context) {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream inputStream = assetManager.open("about.properties");
            properties.load(inputStream);
        } catch (Exception ex) {
            FlyveLog.e(ex.getMessage());
            isLoaded = false;
        }
        isLoaded = true;
    }

    public Boolean getIsLoaded() {
        return isLoaded;
    }

    public String getVersion() {
        return properties.getProperty("about.version");
    }

    public String getBuild() {
        return properties.getProperty("about.build");
    }

    public String getDate() {
        return properties.getProperty("about.date");
    }

    public String getCommit() {
        return properties.getProperty("about.commit");
    }

    public String getCommitFull() {
        return properties.getProperty("about.commitFull");
    }

    public String getGithub() {
        return properties.getProperty("about.github");
    }

}
