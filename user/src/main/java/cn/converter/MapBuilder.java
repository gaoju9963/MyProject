package cn.converter;

import cn.converter.entity.MapEntity;
import cn.converter.impl.MappingManagerImpl;
import cn.function.TwoArg;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

/**
 * Created by pengshu on 2016/11/5.
 */
public class MapBuilder {

    @Resource
    private BuilderConfig builderConfig;

    private MapBuilder(BuilderConfig builderConfig) {
        this.builderConfig = builderConfig;
    }

    public MappingManager builderMapping() {
        MappingManager mappingManager = new MappingManagerImpl(builderConfig);
        return mappingManager;
    }

    public static class BuilderConfig {
        private List<MapEntity> mapEntities;
        //记录没有映射到的列，TwoArg记录(序列,列头名)对
        private List<TwoArg> additionalCells;
        private Class clazz;

        private BuilderConfig(String templatePath) throws Exception {
            mapEntities = readMapperFile(templatePath);
        }

        public void setMapEntities(List<MapEntity> mapEntities) {
            this.mapEntities = mapEntities;
        }

        public List<TwoArg> getAdditionalCells() {
            return additionalCells;
        }

        public void setAdditionalCells(List additionalCells) {
            this.additionalCells = additionalCells;
        }
        /**
         * 读取映射文件
         *
         * @param templatePath
         * @return 返回映射实体类集合
         * @throws Exception
         */
        private List<MapEntity> readMapperFile(String templatePath) throws Exception {
            File file = new File(templatePath);
            StringBuffer sb = new StringBuffer();
            String line;
            BufferedReader br = null;
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            return new Gson().fromJson(new String(sb.toString().getBytes("GBK"),"utf-8"), new TypeToken<List<MapEntity>>() {
            }.getType());
        }

        public List<MapEntity> getMapEntities() {
            return mapEntities;
        }

        public static MapBuilder getMapBuilder(String templatePath) throws Exception {
            return new MapBuilder(new BuilderConfig(templatePath));
        }
    }
}
