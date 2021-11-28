### 1. Which of the following activities cannot be automated?
- [ ] Test execution
- [ ] Exploratory Testing
- [x] Discussing testability issues
- [ ] Test data generation

### 2. How do we describe a good unit test?
- [ ] Flawless, Ready, Self-healing, True, Irresistible
- [ ] Red, Green, Refactor
- [x] Fast, Repeatable, Self-validating, Timely, Isolated
- [ ] Tests should be dependent on other tests

###3. When is it a good idea to use XPath selectors
- [ ] When CSS or other selectors are not an option or would be brittle and hard to maintain
- [ ] When we need to find an element based on parent/child/sibling relationship
- [ ] When an element is located deep within the HTML (or DOM) structure
- [x] All the above

###4. Describe the TDD process

We use the Red, Green, Refactor concept. First write the tests which cover the functionality
and different possible cases. After that we write the code necessary for the function to work.
When the tests pass we refactor the code and repeat the process.

###5. Write 2 test cases or scenarios for a String Calculator application, which has a method `calculate()` that takes a string of two numbers separated by a comma as input, and returns the sum.

- Given the input `" "` when the method `calculate()` is called then I should see a `WrongInputException`
- Given the input `-(5,3)` when the method `calculate()` is called then I should see `2` as a result