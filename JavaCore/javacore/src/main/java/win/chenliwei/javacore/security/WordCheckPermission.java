/**
 * @author: ChenLiwei
 * 2017-03-23
 * WordCheckPermission.java
 * Comments: A permission that checks for bad words: sex,drugs and c++
 */
package win.chenliwei.javacore.security;

import java.security.Permission;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class WordCheckPermission extends Permission {
	private static final long serialVersionUID = 1L;
	private String action;

	public WordCheckPermission(String name, String action) {
		super(name);
		this.action = action;
	}

	public String getAction() {
		return action;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getName(),action);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WordCheckPermission other = (WordCheckPermission) obj;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!Objects.equals(action, other.action))
			return false;
		if("insert".equals(action)) return Objects.equals(getName(), other.getName());
		else if("avoid".equals(action)) return badWordSet().equals(other.badWordSet());
		else return true;
	}

	private Set<String> badWordSet() {
		Set<String> set = new HashSet<>();
		set.addAll(Arrays.asList(getName().split(",")));
		return set;
	}

	@Override
	public String getActions() {
		return action;
	}

	@Override
		/*
		 * (non-Javadoc)
		 * @see java.security.Permission#implies(java.security.Permission)
		 * implies function validate the permissions, it defines two action: avoid and insert
		 * if you can define either action in the policy file, here we define avoid some words as the policy
		 * you must be curious about the insert, that is because we didn't define for insert action
		 * if replace avoid with insert in policy file, you will see the different
		 */
	   public boolean implies(Permission other)
	   {
	      if (!(other instanceof WordCheckPermission)) return false;
	      WordCheckPermission b = (WordCheckPermission) other;
	      //Here, the action is the action exactly policy file, but other.action is passed in from outside
	      if (action.equals("insert"))
	      {
	         return b.action.equals("insert") && getName().indexOf(b.getName()) >= 0;
	      }
	      else if (action.equals("avoid"))
	      {
	         if (b.action.equals("avoid")) return b.badWordSet().containsAll(badWordSet());
	         else if (b.action.equals("insert"))
	         {
	            for (String badWord : badWordSet())
	               if (b.getName().indexOf(badWord) >= 0) return false;
	            return true;
	         }
	         else return false;
	      }
	      else return false;
	   }

}
