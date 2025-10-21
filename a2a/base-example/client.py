import logging

from typing import Any
from uuid import uuid4

import httpx

from a2a.client import A2ACardResolver, A2AClient
from a2a.types import (
    AgentCard,
    MessageSendParams,
    SendMessageRequest,
    SendStreamingMessageRequest,
)
from a2a.utils.constants import (
    AGENT_CARD_WELL_KNOWN_PATH
)

AGENT_BASE_ULR = 'http://localhost:9999'

async def main() -> None:
    #Logger setup
    logging.basicConfig(level=logging.INFO)
    logger = logging.getLogger(__name__)

    #HTTP CLIENT
    async with httpx.AsyncClient() as httpx_client:
        resolver = A2ACardResolver(
            httpx_client=httpx_client,
            base_url=AGENT_BASE_ULR,
        )
        
        #Agent cartd fetching
        final_agent_card_to_use: AgentCard | None = None
        try:
            logger.info(f'Attempting to fetch public agent card from: {AGENT_BASE_ULR}{AGENT_CARD_WELL_KNOWN_PATH}')
            _public_card = (
                await resolver.get_agent_card()
            )  # Fetches from default public path
            logger.info('Successfully fetched public agent card:')
            logger.info(_public_card.model_dump_json(indent=2, exclude_none=True))
            final_agent_card_to_use = _public_card
            logger.info('\nUsing PUBLIC agent card for client initialization (default).')
        except Exception as e:
            logger.error(
                f'Critical error fetching public agent card: {e}', exc_info=True
            )
            raise RuntimeError(
                'Failed to fetch the public agent card. Cannot continue.'
            ) from e

        #A2A Client initialization
        client = A2AClient(httpx_client=httpx_client, agent_card=final_agent_card_to_use)
        logger.info('A2AClient initialized.')

        #Send a message request
        send_message_payload: dict[str, Any] = {
            'message': {
                'role': 'user',
                'parts': [
                    {'kind': 'text', 'text': 'how much is 10 USD in INR?'}
                ],
                'messageId': uuid4().hex,
            },
        }
        request = SendMessageRequest(
            id=str(uuid4()), params=MessageSendParams(**send_message_payload)
        )
        response = await client.send_message(request)
        print(response.model_dump(mode='json', exclude_none=True))

        #Send a streaming message request
        streaming_request = SendStreamingMessageRequest(
            id=str(uuid4()), params=MessageSendParams(**send_message_payload)
        )
        stream_response = client.send_message_streaming(streaming_request)
        async for chunk in stream_response:
            print(chunk.model_dump(mode='json', exclude_none=True))

if __name__ == '__main__':
    import asyncio

    asyncio.run(main())