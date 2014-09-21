package jp.tomorrowkey.appengine.requestrecorder.controller;

import jp.tomorrowkey.appengine.requestrecorder.meta.RequestRecordMeta;
import jp.tomorrowkey.appengine.requestrecorder.model.RequestRecord;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;
import org.slim3.util.StringUtil;

import java.io.PrintWriter;
import java.util.List;

public class IndexController extends Controller {

    @Override
    protected Navigation run() throws Exception {
        String userAgent = request.getHeader("User-Agent");
        if (userAgent.startsWith("Mozilla/")) {
            List<RequestRecord> requestRecordList =
                Datastore.query(RequestRecordMeta.get()).asList();
            requestScope("requestRecordList", requestRecordList);

            return forward("/index.jsp");
        }

        if (StringUtil.isEmpty(userAgent)) {
            PrintWriter writer = response.getWriter();
            writer.append("UserAgent must not be empty\n");
            writer.flush();
            return null;
        }

        RequestRecord requestRecord = new RequestRecord(request);
        RequestRecordMeta meta = RequestRecordMeta.get();
        List<RequestRecord> requestRecordList =
            Datastore
                .query(meta)
                .filter(meta.userAgent.equal(userAgent))
                .asList();

        long timestamp = System.currentTimeMillis();
        if (requestRecordList.isEmpty()) {
            requestRecord.setCount(1);
            requestRecord.setCreateAt(timestamp);
            requestRecord.setUpdateAt(timestamp);
            Datastore.put(requestRecord);
        } else {
            requestRecord = requestRecordList.get(0);
            requestRecord.incrementCount();
            requestRecord.setUpdateAt(timestamp);
            Datastore.put(requestRecord);
        }

        PrintWriter writer = response.getWriter();
        writer.append("Your Request is recorded\n");
        writer.flush();
        return null;
    }
}
