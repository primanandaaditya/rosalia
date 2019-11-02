package org.apache.cordova.file;

import android.util.SparseArray;
import org.apache.cordova.CallbackContext;

class PendingRequests {
    /* access modifiers changed from: private */
    public int currentReqId = 0;
    private SparseArray<Request> requests = new SparseArray<>();

    public class Request {
        private int action;
        private CallbackContext callbackContext;
        private String rawArgs;
        /* access modifiers changed from: private */
        public int requestCode;

        private Request(String rawArgs2, int action2, CallbackContext callbackContext2) {
            this.rawArgs = rawArgs2;
            this.action = action2;
            this.callbackContext = callbackContext2;
            this.requestCode = PendingRequests.this.currentReqId = PendingRequests.this.currentReqId + 1;
        }

        public int getAction() {
            return this.action;
        }

        public String getRawArgs() {
            return this.rawArgs;
        }

        public CallbackContext getCallbackContext() {
            return this.callbackContext;
        }
    }

    PendingRequests() {
    }

    public synchronized int createRequest(String rawArgs, int action, CallbackContext callbackContext) {
        Request req;
        req = new Request(rawArgs, action, callbackContext);
        this.requests.put(req.requestCode, req);
        return req.requestCode;
    }

    public synchronized Request getAndRemove(int requestCode) {
        Request result;
        result = (Request) this.requests.get(requestCode);
        this.requests.remove(requestCode);
        return result;
    }
}
