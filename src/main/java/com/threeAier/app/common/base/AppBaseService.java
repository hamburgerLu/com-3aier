package com.threeAier.app.common.base;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.threeAier.app.dao.Paginate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;

import java.util.*;


public abstract class AppBaseService {

    private static final Log logger = LogFactory.getLog(AppBaseService.class);

    /**
     * 创建分页
     *
     * @param pageIndex
     * @param pageSize
     * @param dataList
     * @return
     */
    protected Paginate createPaginate(int pageIndex, int pageSize, List dataList) {
        Paginate paginate = new Paginate(pageIndex, pageSize);

        if (CollectionUtils.isEmpty(dataList)) {
            paginate.setPageList(Collections.emptyList());
        } else {
            paginate.setPageList(dataList);
        }

        if (dataList instanceof Page) {
            Page paginator = ((Page) dataList);
            if (paginator != null) {
                paginate.setTotalCount((int) paginator.getTotal());
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("totalCount:" + paginate.getTotalCount() + "\ttotalPages:"
                    + paginate.getTotalPage() + "\tpageIndex:" + pageIndex
                    + "\tpageSize:" + pageSize);
        }
        return paginate;
    }

    /**
     * 对批量操作的集合数据进行分页
     *
     * @param batchList 批量操作的对象集合
     * @param pageSize  每页数据条数，根据对象的字段数量确定,计算规则:2100/字段数量
     * @return 返回一个List, 里面是分页后的List集合
     */
    protected List<List> pagingBatchOperationList(List batchList, int pageSize) {
        List<List> returnList = new ArrayList<List>();
        if (CollectionUtils.isEmpty(batchList)) {
            return returnList;
        }
        int beginIndex = 0;
        int allSize = batchList.size();
        int step = allSize % pageSize == 0 ? allSize / pageSize : allSize / pageSize + 1;
        int toIndex = 0;
        for (int i = 0; i < step; i++) {
            toIndex = (i + 1) * pageSize;
            if (toIndex > allSize) {
                toIndex = allSize;
            }
            beginIndex = i * pageSize;
            returnList.add(batchList.subList(beginIndex, toIndex));
        }
        return returnList;
    }

    protected void setPage(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
    }

    protected void setPage(int pageNo, int pageSize, String orderBy) {
        PageHelper.startPage(pageNo, pageSize, orderBy);
    }


}
