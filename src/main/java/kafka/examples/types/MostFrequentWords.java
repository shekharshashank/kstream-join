package kafka.examples.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.TreeSet;

/**
 * @author shashshe
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "sortedMostFrequentWords"
})
public class MostFrequentWords {
    private ObjectMapper mapper = new ObjectMapper();
    private final TreeSet<WordCount> sortedMostFrequentWords = new TreeSet<>((o1,o2)-> {
        final int result =o2.getCount().compareTo(o1.getCount());
        if (result != 0)
            return result;
        else
            return o1.getWord().compareTo(o2.getWord());
    });

    public void add(WordCount wc){
        sortedMostFrequentWords.add(wc);
        if(sortedMostFrequentWords.size()>3){
            sortedMostFrequentWords.remove(sortedMostFrequentWords.last());
        }
    }

    public void remove(WordCount wc){
        sortedMostFrequentWords.remove(wc);
    }

    @JsonProperty("sortedMostFrequentWords")
    public String getSortedMostFrequentWords() throws JsonProcessingException {
        return mapper.writeValueAsString(sortedMostFrequentWords);
    }

    @JsonProperty("sortedMostFrequentWords")
    public void setSortedMostFrequentWords(String mostFrequentWords) throws IOException {
        WordCount[] topWords = mapper.readValue(mostFrequentWords , WordCount[].class);
        for(WordCount wc : topWords){
            add(wc);
        }
    }


}
