
package kafka.examples.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Word",
    "Count"
})
public class WordCount {

    @JsonProperty("Word")
    private String word;
    @JsonProperty("Count")
    private Long count;

    @JsonProperty("Word")
    public String getWord() {
        return word;
    }

    @JsonProperty("Word")
    public void setWord(String word) {
        this.word = word;
    }

    public WordCount withWord(String word) {
        this.word = word;
        return this;
    }

    @JsonProperty("Count")
    public Long getCount() {
        return count;
    }

    @JsonProperty("Count")
    public void setCount(Long count) {
        this.count = count;
    }

    public WordCount withCount(Long count) {
        this.count = count;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("word", word).append("count", count).toString();
    }

}
