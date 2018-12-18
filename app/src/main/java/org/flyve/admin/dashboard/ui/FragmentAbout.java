/*
 * Copyright (C) 2017 Teclib'
 *
 * This file is part of Flyve MDM Inventory Agent Android.
 *
 * Flyve MDM Inventory Agent Android is a subproject of Flyve MDM. Flyve MDM is a mobile
 * device management software.
 *
 * Flyve MDM Android is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * Flyve MDM Inventory Agent Android is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * ------------------------------------------------------------------------------
 * @author    Rafael Hernandez - rafaelje
 * @copyright Copyright (c) 2017 Flyve MDM
 * @license   GPLv3 https://www.gnu.org/licenses/gpl-3.0.html
 * @link      https://github.com/flyve-mdm/flyve-mdm-android-inventory-agent/
 * @link      http://www.glpi-project.org/
 * @link      https://flyve-mdm.com/
 * ------------------------------------------------------------------------------
 */
package org.flyve.admin.dashboard.ui;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.flyve.admin.dashboard.R;
import org.flyve.admin.dashboard.utils.EnvInfoAbout;

import androidx.fragment.app.Fragment;

public class FragmentAbout extends Fragment {

    /**
     * Called when the activity is starting, inflates the activity's UI
     * @param inflater
     * @param container
     * @param savedInstanceState
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.activity_about, container, false);

        TextView txtAbout = (TextView) v.findViewById(R.id.txtAbout);

        EnvInfoAbout enviromentInfo = new EnvInfoAbout(FragmentAbout.this.getContext());

        if(enviromentInfo.getIsLoaded()) {
            txtAbout.setText(Html.fromHtml(aboutStr(enviromentInfo.getVersion(), enviromentInfo.getBuild(), enviromentInfo.getDate(), enviromentInfo.getCommit(), enviromentInfo.getCommitFull(), enviromentInfo.getGithub())));
            txtAbout.setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            txtAbout.setVisibility(View.GONE);
        }

        return v;
    }

    private String aboutStr(String version, String build, String date, String commit, String commitFull, String github) {
        String str = "Admin Dashboard Agent, version "+ version +", build "+ build +".<br />";
        str += "Built on "+ date +". Last commit <a href='"+github+"/commit/"+commitFull+"'>"+ commit +"</a>.<br />";
        str += "(C) <a href='http://teclib-edition.com/'>Teclib'</a> 2017. Licensed under <a href='https://www.gnu.org/licenses/gpl-3.0.en.html'>GPLv3</a>. <a href='https://flyve-mdm.com/'>Flyve MDM</a> (R)";

        return str;
    }
}
