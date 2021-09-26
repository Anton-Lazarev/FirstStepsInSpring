package training.banking.controller;

import org.springframework.web.bind.annotation.*;
import training.banking.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("transaction")
public class TransactionController {
    private int counterOfTransactions = 6;
    private List<Map<String, String>> transactions = new ArrayList<Map<String, String>>() {
        {
            add(new HashMap<String, String>() {{put("id", "1"); put("amount", "256$");}});
            add(new HashMap<String, String>() {{put("id", "2"); put("amount", "47.4$");}});
            add(new HashMap<String, String>() {{put("id", "3"); put("amount", "368.12$");}});
            add(new HashMap<String, String>() {{put("id", "4"); put("amount", "87.98$");}});
            add(new HashMap<String, String>() {{put("id", "5"); put("amount", "36987.14$");}});
        }
    };

    @GetMapping
    public List<Map<String, String>> fullListOfTransactions() {
        return transactions;
    }

    @GetMapping("{id}")
    public Map<String, String> GET(@PathVariable String id) {
        return transactions.stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Map<String, String> POST(@RequestBody Map<String, String> transaction) {
        transaction.put("id", String.valueOf(counterOfTransactions++));
        transactions.add(transaction);
        return transaction;
    }

    @DeleteMapping({"id"})
    public void DELETE(@PathVariable String id) {
        Map<String, String> transaction = GET(id);
        transactions.remove(transaction);
    }
}
