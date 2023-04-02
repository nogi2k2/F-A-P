#include <iostream>
#include <string>
using namespace std;

class Meal {
public:
    void setpizza(const string& pizza) { m_pizza = pizza; }
    void setcrust(const string& crust) { m_crust = crust; }
    void settoppings(const string& toppings) { m_toppings = toppings; }

    void print() const {
        cout << "Pizza: " << m_pizza << endl;
        cout << "Crust: " << m_crust << endl;
        cout << "Toppings " << m_toppings << endl;
    }

private:
    string m_pizza;
    string m_crust;
    string m_toppings;
};

class MealBuilder {
public:
    MealBuilder& withpizza(const string& pizza) {
        m_pizza = pizza;
        return *this;
    }

    MealBuilder& withcrust(const string& crust) {
        m_crust = crust;
        return *this;
    }

    MealBuilder& withtoppings(const string& toppings) {
        m_toppings = toppings;
        return *this;
    }

    Meal build() const {
        Meal meal;
        meal.setpizza(m_pizza);
        meal.setcrust(m_crust);
        meal.settoppings(m_toppings);
        return meal;
    }

private:
    string m_pizza;
    string m_crust;
    string m_toppings;
};

int main() {
    MealBuilder builder;
    Meal meal = builder.withpizza("Pineapple").withcrust("Cheese").withtoppings("Tomato").build();
    meal.print();

    return 0;
}