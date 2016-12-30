package cn.converter.impl;

import cn.converter.MapBuilder;
import cn.converter.MappingManager;
import cn.converter.entity.MapEntity;
import cn.function.TwoArg;
import com.model.User;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by pengshu on 2016/11/5.
 */
public class MappingManagerImpl implements MappingManager {

    private MapBuilder.BuilderConfig builderConfig;
    List<User> debtCases = new ArrayList<>();

    public MappingManagerImpl(MapBuilder.BuilderConfig builderConfig) {
        this.builderConfig = builderConfig;
    }

    @Override
    public void mapHeader(List<String> arr) throws Exception {
        List<MapEntity> mapEntities = builderConfig.getMapEntities();
        List additionalCells = new ArrayList<>();
        int index = 0;
        for (String value : arr) {
            boolean matchFalg = false;//匹配成功的标志
            Iterator<MapEntity> it = mapEntities.iterator();
            while (it.hasNext()) {
                MapEntity mapEntity = it.next();
                if (mapEntity.getValue().contains(value)) {
                    mapEntity.setCol(index);
                    mapEntity.setColName(value);
                    matchFalg = true;
                    break;
                }
                if (null != mapEntity.getData() && mapEntity.getData().size() != 0) {
                    Map<Integer, List<MapEntity>> map = mapEntity.getData();
                    Iterator<Integer> it1 = map.keySet().iterator();
                    while (it1.hasNext()) {
                        Integer integer = it1.next();
                        for (MapEntity entity : map.get(integer)) {
                            if (entity.getValue().contains(value)) {
                                entity.setCol(index);
                                entity.setColName(value);
                                matchFalg = true;
                                break;
                            }
                        }
                        if (matchFalg) {
                            break;
                        }
                    }
                }
            }
            if (!matchFalg) {
                additionalCells.add(new TwoArg<>(index, value));
            }
            index++;
        }
        builderConfig.setAdditionalCells(additionalCells);
    }

    /**
     * 映射每一行数据
     *
     * @param rowData 元素TwoArg记录的是（type,value)对
     */
    @Override
    public void mapValue(List<TwoArg> rowData) throws Exception {
        User user = new User();
        for (MapEntity mapEntity : builderConfig.getMapEntities()) {
            if (null == mapEntity.getCol() && !"contacts".equals(mapEntity.getType())) {
                continue;
            }

            switch (mapEntity.getType()) {
                case "String": {
                    this.setFieldValue(user, mapEntity.getKey(), rowData.get(mapEntity.getCol()).getV());
                    break;
                }
                case "Integer": {
                    String value = rowData.get(mapEntity.getCol()).getV().toString();
                    if (StringUtils.isEmpty(value)) {
                        break;
                    }
                    this.setFieldValue(user, mapEntity.getKey(), Double.valueOf(value).intValue());
                    break;
                }
            }
        }
        debtCases.add(user);
    }

    @Override
    public List getResult() {
        return debtCases;
    }

    private void setFieldValue(User user, String fieldName, Object object) throws Exception {
        Field field = user.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(user, object);
    }
}
