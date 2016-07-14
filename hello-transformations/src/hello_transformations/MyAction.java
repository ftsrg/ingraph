package hello_transformations;

import org.eclipse.viatra.query.runtime.api.IMatchProcessor;
import org.eclipse.viatra.query.runtime.api.IPatternMatch;

import relalg.TrimmerUpMatch;

public class MyAction implements IMatchProcessor<IPatternMatch> {

	@Override
	public void process(IPatternMatch match) {
		TrimmerUpMatch m = (TrimmerUpMatch) match;
		
		m.getJoin().setLeftParent(m.getRn1());
	}

}
