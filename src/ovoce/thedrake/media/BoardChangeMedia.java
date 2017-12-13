package ovoce.thedrake.media;

import ovoce.thedrake.game.CaptureOnly;
import ovoce.thedrake.game.StepAndCapture;
import ovoce.thedrake.game.StepOnly;

public interface BoardChangeMedia<T> {
	public T putStepOnly(StepOnly change);
	public T putCaptureOnly(CaptureOnly change);
	public T putStepAndCapture(StepAndCapture change);
}
