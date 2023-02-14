package com.joanmoncho.frasescelebresretrofit.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceManager;

import androidx.preference.PreferenceFragmentCompat;

import com.joanmoncho.frasescelebresretrofit.R;

public class FragmentAjustes extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener{
    private ListPreference lp_algoritmo;

    @Override
    public void onCreatePreferences(Bundle bundle, String rootKey) {
        setPreferencesFromResource(R.xml.opciones, rootKey);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(requireContext());
        prefs.registerOnSharedPreferenceChangeListener(this);
        //lp_algoritmo = findPreference("lp_algoritmo");
        assert lp_algoritmo != null;
        lp_algoritmo.setEnabled(prefs.getBoolean("cbp_cifrado", false));
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        lp_algoritmo.setEnabled(sharedPreferences.getBoolean("cbp_cifrado", false));
    }
}
