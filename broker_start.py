import logging
import asyncio
from hbmqtt.broker import Broker
from hbmqtt.client import MQTTClient, ClientException
from hbmqtt.mqtt.constants import QOS_1

logger = logging.getLogger(__name__)

config = {
    'listeners': {
        'default': {
            'type': 'tcp',
            'bind': 'localhost:9999',    # 0.0.0.0:1883
            'max-connections': 50
        }
    },
    'sys_interval': 10,
    'topic-check': {
        'enabled': True,
         'plugins': ['topic_taboo'],
    }
}

broker = Broker(config)

@asyncio.coroutine
def startBroker():
    yield from broker.start()

@asyncio.coroutine
def brokerGetMessage():
    C = MQTTClient()
    yield from C.connect('mqtt://localhost:9999/')
    yield from C.subscribe([
        ("eliu/test", QOS_1)
    ])
    logger.info('Suscrito!')
    try:
        for i in range(1,100):
            message = yield from C.deliver_message()
            packet = message.publish_packet
            print(packet.payload.data.decode('utf-8'))
    except ClientException as ce:
        logger.error("Excepción : %s" % ce)

if __name__ == '__main__':
    formatter = "[%(asctime)s] :: %(levelname)s :: %(name)s :: %(message)s"
    logging.basicConfig(level=logging.INFO, format=formatter)
    asyncio.get_event_loop().run_until_complete(startBroker())
    asyncio.get_event_loop().run_until_complete(brokerGetMessage())
    asyncio.get_event_loop().run_forever()