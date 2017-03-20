package com.polly.program.api;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class RxManager {
    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public void add(Subscription subscription) {
        mCompositeSubscription.add(subscription);
    }

    public void clear() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions())
            mCompositeSubscription.unsubscribe();
    }
}
