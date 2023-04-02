#include <iostream>
using namespace std;

// Existing interface used by the app to place orders
class IOrder {
public:
    virtual void placeOrder() = 0;
};

// Interface of the third-party order service
class IThirdPartyOrder {
public:
    virtual void confirmOrder() = 0;
};

// Concrete implementation of the existing interface
class Order : public IOrder {
public:
    void placeOrder() override {
        cout << "Placing order via app...\n";
    }
};

// Adapter class which adapts the third-party order service to our existing interface
class ThirdPartyOrderAdapter : public IOrder {
public:
    ThirdPartyOrderAdapter(IThirdPartyOrder* thirdPartyOrder) : m_thirdPartyOrder(thirdPartyOrder) {}

    void placeOrder() override {
        // Use the third-party service to confirm the order
        m_thirdPartyOrder->confirmOrder();
        cout << "Placing order via third-party service...\n";
    }

private:
    IThirdPartyOrder* m_thirdPartyOrder;
};

// Concrete implementation of the third-party order service
class ThirdPartyOrder : public IThirdPartyOrder {
public:
    void confirmOrder() override {
        cout << "Confirming order via third-party service...\n";
    }
};

int main() {
    // Place order via app
    IOrder* order = new Order();
    order->placeOrder();

    // Place order via third-party service using the adapter
    IThirdPartyOrder* thirdPartyOrder = new ThirdPartyOrder();
    IOrder* thirdPartyOrderAdapter = new ThirdPartyOrderAdapter(thirdPartyOrder);
    thirdPartyOrderAdapter->placeOrder();

    return 0;
}