/*
    QuasselDroid - Quassel client for Android
    Copyright (C) 2015 Ken Børge Viktil
    Copyright (C) 2015 Magnus Fjell
    Copyright (C) 2015 Martin Sandsmark <martin.sandsmark@kde.org>

    This program is free software: you can redistribute it and/or modify it
    under the terms of the GNU General Public License as published by the Free
    Software Foundation, either version 3 of the License, or (at your option)
    any later version, or under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either version 2.1 of
    the License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License and the
    GNU Lesser General Public License along with this program.  If not, see
    <http://www.gnu.org/licenses/>.
 */

package com.iskrembilen.quasseldroid.protocol.state;

import android.util.Log;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class IdentityCollection {

    static final String TAG = IdentityCollection.class.getSimpleName();

    private SparseArray<Identity> identities = new SparseArray<>();
    private Set<Integer> ids = new LinkedHashSet<>();

    private static IdentityCollection instance = new IdentityCollection();

    public static IdentityCollection getInstance() {
        return instance;
    }

    public void clear() {
        Log.d(TAG, "clear");
        identities.clear();
        ids.clear();
    }

    public void putIdentity(Identity identity) {
        identities.put(identity.getIdentityId(),identity);
        ids.add(identity.getIdentityId());
        Client.getInstance().getObjects().putObject("Identity",String.valueOf(identity.getIdentityId()),identity);
    }

    public void removeIdentity(Identity identity) {
        Log.d(TAG, "removeidentity: " + identity.getIdentityId());
        identities.remove(identity.getIdentityId());
        ids.remove(identity.getIdentityId());
        Client.getInstance().getObjects().removeObject("Identity",String.valueOf(identity.getIdentityId()));
    }

    public void removeIdentity(int identityId) {
        Log.d(TAG, "removeidentity: " + identityId);
        identities.remove(identityId);
        ids.remove(identityId);
        Client.getInstance().getObjects().removeObject("Identity",String.valueOf(identityId));
    }

    public List<Identity> getIdentities() {
        List<Identity> list = new ArrayList<>(ids.size());
        for (int id : ids) {
            list.add(identities.get(id));
        }
        return list;
    }

    public Identity getIdentity(int id) {
        return identities.get(id);
    }
}
