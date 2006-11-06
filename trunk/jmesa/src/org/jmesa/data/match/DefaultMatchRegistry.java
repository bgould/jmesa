/*
 * Copyright 2004 original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jmesa.data.match;

import java.util.HashMap;
import java.util.Map;

/**
 * @since 2.0
 * @author Jeff Johnston
 */
public class DefaultMatchRegistry implements MatchRegistry {
	private Map<MatchKey, Match> matches = new HashMap<MatchKey, Match>();
	
	public void addMatch(MatchKey key, Match match) {
		matches.put(key, match);
	}
	
	public MatchKey getMatchKey(MatchKey key) {
		Match match = matches.get(key);
		
		if (match == null) {
			key.setId(null);
			match = matches.get(key);
		}
		
		if (match == null) {
			key.setProperty(null);
			match = matches.get(key);
		}
		
		return key;
	}

	public Match getMatch(MatchKey key) {
		return matches.get(key);
	}
}
