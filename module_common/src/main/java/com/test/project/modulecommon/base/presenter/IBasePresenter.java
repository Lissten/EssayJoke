package com.test.project.modulecommon.base.presenter;

import com.test.project.modulecommon.observer.IEssayJokeObsever;

/**
 * Presenter基础接口，所有Presenter必须继承或实现此接口
 */
public interface IBasePresenter extends IEssayJokeObsever {
	void start();
}
