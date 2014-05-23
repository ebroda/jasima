/*******************************************************************************
 * Copyright (c) 2010, 2014 Torsten Hildebrandt and jasima contributors
 *
 * This file is part of jasima, v1.1.
 *
 * jasima is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * jasima is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jasima.  If not, see <http://www.gnu.org/licenses/>.
 *
 * $Id$
 *******************************************************************************/
package jasima_gui.pref;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;

public class Initializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		for (Field f : Pref.class.getFields()) {
			int requiredMods = Modifier.PUBLIC | Modifier.STATIC
					| Modifier.FINAL;
			if (((f.getModifiers() & requiredMods) == requiredMods)
					&& Pref.class.isAssignableFrom(f.getType())) {
				try {
					((Pref) f.get(null)).initDefault();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
