package com.processor.analytics;

import io.github.mainstringargs.alphavantagescraper.output.quote.data.StockQuote;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import java.time.LocalDate;

public class StockQuoteCodec implements Codec<StockQuote> {
    @Override
    public StockQuote decode(BsonReader reader, DecoderContext decoderContext) {
        // Implement decoding logic here
        reader.readStartDocument();
        StockQuote stockQuote = new StockQuote(
                reader.readString("symbol"),
                reader.readDouble("open"),
                reader.readDouble("high"),
                reader.readDouble("low"),
                reader.readDouble("close"),
                reader.readInt32("volume"),
                LocalDate.now(),
                reader.readDouble("previousClose"),
                reader.readDouble("change"),
                reader.readDouble("changePercent"));
        reader.readEndDocument();
        return stockQuote;
    }

    @Override
    public void encode(BsonWriter writer, StockQuote value, EncoderContext encoderContext) {
        // Implement encoding logic here
        writer.writeStartDocument();
        writer.writeString("symbol", value.getSymbol());
        writer.writeDouble("open", value.getOpen());
        writer.writeDouble("high", value.getHigh());
        writer.writeDouble("low", value.getLow());
        writer.writeDouble("previousClose", value.getPreviousClose());
        writer.writeDouble("change", value.getChange());
        writer.writeDouble("changePercent", value.getChangePercent());
        writer.writeInt32("volume", (int) value.getVolume());
        writer.writeString("latestTradingDay", value.getLatestTradingDay().toString());
        writer.writeEndDocument();
    }

    @Override
    public Class<StockQuote> getEncoderClass() {
        return StockQuote.class;
    }
}
