package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pojos.gmiBankPojos.StatePojos;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CountryPojo {
    private String name;
    private List<StatePojos> states;

    public CountryPojo() {
    }

    public CountryPojo(String name, List<StatePojos> states) {
        this.name = name;
        this.states = states;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StatePojos> getStates() {
        return states;
    }

    public void setStates(List<StatePojos> states) {
        this.states = states;
    }

    @Override
    public String toString() {
        return "CountryPojo{" +
                "name='" + name + '\'' +
                ", states=" + states +
                '}';
    }
}
