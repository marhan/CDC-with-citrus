package com.github.svettwer.citruscdc.contracts.utils;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;

import java.util.Collections;
import java.util.List;

public class CsvReader {

    private static Logger logger = LogManager.getLogger(CsvReader.class);

    public <T> List<T> loadObjectList(final Class<T> type, final Resource dataResource) {
        try {
            final CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            final CsvMapper mapper = new CsvMapper();
            final MappingIterator<T> readValues =
                    mapper.readerFor(type).with(bootstrapSchema).readValues(dataResource.getFile());
            return readValues.readAll();
        } catch (final Exception e) {
            logger.error("Error occurred while loading object list from file " + dataResource, e);
            return Collections.emptyList();
        }
    }

}
