package bof;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class CachableObject {
    private String name;
    private UUID id;
    private int price;
    private List<String> tags;
}
