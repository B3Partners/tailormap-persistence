/*
 * Copyright (C) 2011-2016 B3Partners B.V.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package nl.tailormap.viewer.config.services;

import org.json.JSONException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Map;

/**
 *
 * @author Matthijs Laan
 */
@Entity
@DiscriminatorValue(WFSFeatureSource.PROTOCOL)
public class WFSFeatureSource extends FeatureSource {
    public static final String PROTOCOL = "wfs";

    public WFSFeatureSource() {
        super();
    }

    public WFSFeatureSource(Map<String,String> params) throws JSONException {
        super();

        setUrl(params.get("WFSDataStoreFactory:GET_CAPABILITIES_URL"));
        setUsername(params.get("WFSDataStoreFactory:USERNAME"));
        setPassword(params.get("WFSDataStoreFactory:PASSWORD"));
    }

}
