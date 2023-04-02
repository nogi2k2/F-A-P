#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;

class Observer {
public:
    virtual ~Observer() {}
    virtual void update(const string& message) = 0;
};

class Subject {
public:
    void addObserver(Observer* observer) {
        m_observers.push_back(observer);
    }

    void removeObserver(Observer* observer) {
        auto it = find(m_observers.begin(), m_observers.end(), observer);
        if (it != m_observers.end()) {
            m_observers.erase(it);
        }
    }

    void notifyObservers(const string& message) {
        for (auto observer : m_observers) {
            observer->update(message);
        }
    }

    void orderReceived(const string& restaurant, const string& itemName) {
        string message = "New order received from " + restaurant + " for " + itemName;
        notifyObservers(message);
    }

private:
    vector<Observer*> m_observers;
};

class CustomerNotification : public Observer {
public:
    CustomerNotification(const string& customerName) : m_customerName(customerName) {}

    void update(const string& message) override {
        cout << "Hi " << m_customerName << ", " << message << endl;
    }

private:
    string m_customerName;
};

int main() {
    Subject subject;

    CustomerNotification customer1("John");
    CustomerNotification customer2("Sarah");

    subject.addObserver(&customer1);
    subject.addObserver(&customer2);

    subject.orderReceived("Pizza Palace", "Pepperoni Pizza");

    return 0;
}