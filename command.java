#include <iostream>
#include <vector>
#include <string>
#include <algorithm> 
using namespace std;

class Command {
public:
    virtual ~Command() {}
    virtual void execute() = 0;
};

class Order {
public:
    void addItem(const string& itemName) {
        m_items.push_back(itemName);
    }

    void removeItem(const string& itemName) {
        auto it = find(m_items.begin(), m_items.end(), itemName);
        if (it != m_items.end()) {
            m_items.erase(it);
        }
    }

    void printItems() const {
        cout << "Items in order:" << endl;
        for (auto item : m_items) {
            cout << "- " << item << endl;
        }
    }

private:
    vector<string> m_items;
};

class AddItemCommand : public Command {
public:
    AddItemCommand(Order* order, const string& itemName) : m_order(order), m_itemName(itemName) {}

    void execute() override {
        m_order->addItem(m_itemName);
    }

private:
    Order* m_order;
    string m_itemName;
};

class RemoveItemCommand : public Command {
public:
    RemoveItemCommand(Order* order, const string& itemName) : m_order(order), m_itemName(itemName) {}

    void execute() override {
        m_order->removeItem(m_itemName);
    }

private:
    Order* m_order;
    string m_itemName;
};

class CommandInvoker {
public:
    void setCommand(Command* command) {
        m_command = command;
    }

    void executeCommand() {
        if (m_command) {
            m_command->execute();
        }
    }

private:
    Command* m_command = nullptr;
};

int main() {
    Order order;

    AddItemCommand addItemCommand(&order, "Pepperoni Pizza");
    RemoveItemCommand removeItemCommand(&order, "Pepperoni Pizza");

    CommandInvoker invoker;

    invoker.setCommand(&addItemCommand);
    invoker.executeCommand();

    order.printItems();

    invoker.setCommand(&removeItemCommand);
    invoker.executeCommand();

    order.printItems();

    return 0;
}